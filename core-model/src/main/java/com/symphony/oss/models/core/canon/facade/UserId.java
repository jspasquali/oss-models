/**
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/TypeDef/_.java.ftl
 *		Template version	   1.0
 *  At                  2019-03-22 09:19:04 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.core.canon.UserIdTypeDef;

/**
 * User IDs in SBE contain a 36 bit per pod user Id and a 27 bit podId
 * which are OR'ed together to form a single 63 bit value which is stored in a long integer,
 * the most significant (sign) bit, in bit position 0, is always zero the podId is in bit
 * positions 1-28 and the per tenant user Id is in bit positions 29-63.
 * 
 * Internal pod IDs are not unique so these userIds are not globally unique and
 * we therefore have a separate "external podId" and code in
 * https://github.com/SymphonyOSF/SBE/blob/dev/commons/runtimecommons/src/main/java/com/symphony/runtime/commons/util/UserIDUtil.java
 * to manipulate these IDs to replace the "internal podId" with an "externalPodId"
 * 
 * In the 2.0 space we use a globally unique hash to refer to a user called the principalId
 * which is constructed from the external userId, i.e. the userId with the podId part replaced
 * with the pod's externalPodId.
 * 
 * This class takes an internal and external podId in its constructor which allows it to map internal userId
 * values to their external equivalents where necessary. Where a userId value is passed into methods in this
 * class it is always safe to pass either internal or external values.
 *
 * 63 bit userId      0x7FFFFFFFFFFFFFFFL
 *                      0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
 * 27 bit podId       0x7FFFFFF000000000L
 *                      0111 1111 1111 1111 1111 1111 1111 0000 0000 0000 0000 0000 0000 0000 0000 0000
 * 36 bit user part   0x0000000FFFFFFFFFL
 *                      0000 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111
 * @author Bruce Skingle
 */
@Immutable
public class UserId extends UserIdTypeDef
{
  /** All bits which may be non-zero in an SBE userId */
  public static final long USER_ID_MASK       = 0x7FFFFFFFFFFFFFFFL;  // 0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
  
  /** Bits representing the pod part in an SBE userId */
  public static final long USER_ID_POD_MASK   = 0x7FFFFFF000000000L;  // 0111 1111 1111 1111 1111 1111 1111 0000 0000 0000 0000 0000 0000 0000 0000 0000
  
  /** Bits representing the user part in an SBE userId */
  public static final long USER_ID_USER_MASK  = 0x0000000FFFFFFFFFL;  // 0000 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111
  
  /** Number of bits by which the pod part of an SBE userId are shifted */
  public static final int  USER_ID_POD_SHIFT  = 36;
  
  private static Builder theBuilder = new Builder();
  
  /**
   * Return a mapping function to map user IDs from the given internal to the given external pod id.
   * 
   * @param internalPodId The pod's internal pod ID
   * @param externalPodId The pod's external pod ID.
   * 
   * @return A mapping function.
   */
  public static IUserIdFunction getUserIdFunction(long internalPodId, long externalPodId)
  {
    /*
     * If the internal and external pod IDs are the same, which they almost always are, then there is nothing to do, so
     * we use a closure as an optimisation as it allows us to test for the common case once, here.
     */
    if(internalPodId == externalPodId)
    {
      // The internal and external IDs are the same, so this is the identity function.
      
      return (x) -> x;
    }
    else
    {
      final long internalPodIdPart = internalPodId << USER_ID_POD_SHIFT;
      final long externalPodIdPart = externalPodId << USER_ID_POD_SHIFT;
      
      // If the podId is our internal podId then replace it with our external podId.
      
      return (internalOrExternalUserId) ->
      {
        if((internalOrExternalUserId.longValue() & USER_ID_POD_MASK) == internalPodIdPart)
        {
          return PodAndUserId.newBuilder().build((internalOrExternalUserId.longValue() & USER_ID_USER_MASK) | externalPodIdPart);
        }
        else
        {
          return internalOrExternalUserId;
        }
      };
    }
  }

  /**
   * Extract the local user part from an internal or external userId.
   * 
   * @param internalOrExternalUserId  An SBE user ID, it makes no difference
   *                                  if its the internal or external version as
   *                                  we will extract away the podId part.
   * @return                          The local user part of the given ID.
   */
  public static long extractUserId(long internalOrExternalUserId)
  {
    return internalOrExternalUserId & USER_ID_USER_MASK;
  }
  

  /**
   * Extract the pod part from an internal or external userId.
   * 
   * @param internalOrExternalUserId  An SBE user ID.
   * @return                          The pod part of the given ID.
   */
  public static int extractPodId(long internalOrExternalUserId)
  {
    return (int)((internalOrExternalUserId & USER_ID_POD_MASK) >> USER_ID_POD_SHIFT);
  }
  
  /**
   * Replace the pod part in an internal or external userId.
   * 
   * @param internalOrExternalUserId  An SBE user ID.
   * @param podId                     The podId to overlay into the internalOrExternalUserId.
   * @return                          The local user part of the given ID.
   */
  public static long replacePodId(long internalOrExternalUserId, long podId)
  {
    return (internalOrExternalUserId & USER_ID_USER_MASK) | (podId << USER_ID_POD_SHIFT);
  }
  
  private UserId(@Nonnull Long value)
  {
    super(value);
  }

  /**
   * Return a new Builder.
   *
   * The generated version of this builder is stateless and so it is safe to return a 
   * reference to a shared instance, if you add functionality to this builder which is
   * not thread safe then you need to change this to return new Builder();
   *
   * @return A new Builder.
   */
  public static Builder newBuilder()
  {
    return theBuilder;
  }
  
  /**
   * Builder for UserId.
   */
  public static class Builder extends UserIdTypeDef.Builder
  {
    private Builder()
    {
    }
    
    @Override
    public UserId build(@Nonnull Long value)
    {
      if(value == null)
        throw new NullPointerException("value is required.");
      
      return new UserId(extractUserId(value));
    }
    
    @Override
    public Long toValue(UserId instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */