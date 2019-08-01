/*
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
 */

package com.symphony.oss.models.fundamental.canon.facade;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.common.hash.HashProvider;

import com.symphony.oss.models.fundmental.canon.ISecurityContextRotationId;
import com.symphony.oss.models.fundmental.canon.SecurityContextRotationId;
import com.symphony.oss.models.fundmental.canon.SecurityContextRotationIdType;

public class TestFundamentalId
{
  @Test
  public void testFundamentalId()
  {
    Hash         securityContextBaseHash  = HashProvider.getHashOf("securityContextBaseHash".getBytes());
    
    ISecurityContextRotationId  threadSecurityContextId = new SecurityContextRotationId.Builder()
        .withType(SecurityContextRotationIdType.SECURITY_CONTEXT)
        .withSecurityContextBaseHash(securityContextBaseHash)
        .withRotationId(RotationId.ZERO)
        .build();
    
    IFundamentalObject idObject = new FundamentalObject.IdBuilder()
      .withFundamentalId(threadSecurityContextId)
      .build();
    
    Hash threadSecurityContextIdHash = HashProvider.getHashOf(idObject.getHashType().getValue(), threadSecurityContextId.serialize());
    
    System.out.println("threadSecurityContextId  = " + threadSecurityContextId);
    System.out.println("threadSecurityContextIdHash  = " + threadSecurityContextIdHash);
    System.out.println("idObject.getAbsoluteHash()  = " + idObject.getAbsoluteHash());
    System.out.println("idObject  = " + idObject);
    
    assertEquals(idObject.getAbsoluteHash(), threadSecurityContextIdHash);
  }
}
