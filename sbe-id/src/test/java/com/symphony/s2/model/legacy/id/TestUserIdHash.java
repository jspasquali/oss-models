/*
 *
 *
 * Copyright 2018 Symphony Communication Services, LLC.
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

package com.symphony.s2.model.legacy.id;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundamental.canon.facade.IUserIdObject;
import com.symphony.oss.models.fundamental.canon.facade.PodAndUserId;
import com.symphony.oss.models.fundamental.canon.facade.UserId;
import com.symphony.oss.models.fundamental.canon.facade.UserIdObject;
import com.symphony.oss.models.sbe.id.SbeIdFactory;

/**
 * Unit test showing how you could calculate a user hash without using the provided Java libraries
 * e.g. if you are implementing in another language.
 * 
 * @author Bruce Skingle
 *
 */
public class TestUserIdHash
{
  private static final int INTERNAL_POD_ID = 1;
  private static final int EXTERNAL_POD_ID = 167;
  private static final int LOCAL_USER_ID = 22;
  private static final long EXTERNAL_USER_ID = 11476152614934L;
  
  private final SbeIdFactory factory_ = new SbeIdFactory(INTERNAL_POD_ID, EXTERNAL_POD_ID);
  
  
  /**
   * Test that the hard coded EXTERNAL_USER_ID value matches the EXTERNAL_POD_ID and LOCAL_USER_ID
   */
  @Test
  public void testUserId()
  {
    long userId = UserId.replacePodId(UserId.newBuilder()
        .build((long)LOCAL_USER_ID).getValue(), EXTERNAL_POD_ID);
    
    assertEquals(EXTERNAL_USER_ID, userId);
  }

  /**
   * Calculate a userId hash without using the libraries.
   * 
   * @throws NoSuchAlgorithmException If SHAR-256 is not a valid algorithm, which hopefully will never happen.
   */
  @Test
  public void testUserIdHash() throws NoSuchAlgorithmException
  {
    /* Calculate the hash using the library first so we can assert that the manually computed
     * value matches the library computed one. 
     */
    PodAndUserId userId = PodAndUserId.newBuilder().build(EXTERNAL_USER_ID);
    Hash userIdHash = factory_.userId(userId).getAbsoluteHash();
    
    IUserIdObject userIdObject = new UserIdObject.Builder()
        .withPodAndUserId(userId)
        .build();
    
    Hash userIdHash2 = userIdObject.getAbsoluteHash();

    assertEquals(userIdHash, userIdHash2);
    
    // This is the user ID we are going to manually calculate the hash of
    long externalUserId = EXTERNAL_USER_ID;
    
    
    /* ************************************************************************************************************
     * 
     * This is how you would calculate a userId hash from a non-Java environment.
     * The userIdObjectFormat needs to be exactly the string below with exactly the whitespace in this
     * example (LF line endings and two space indent).
     * 
     * Obviously you woud ignore the assert statements when transcribing to another programming language.
     * 
     * ************************************************************************************************************/
    
    String userIdObjectFormat = "{\n" + 
        "  \"_type\":\"com.symphony.s2.model.fundamental.UserIdObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"hashType\":1,\n" + 
        "  \"podId\":%d,\n" + 
        "  \"userId\":%d\n" + 
        "}\n";
    
    /*
     *  Extract the pod and local user ID Parts from the external user ID
     *  
     *  NOTE THAT YOU NEED TO USE AN EXTERNAL USERID, THE POD ID PART MUST NOT BE 1
     *  THAT BEING THE INTERNAL POD ID OF GS AND PUBMY. 1 IS NEVER A VALID EXTERNAL
     *  POD ID
     */
    int podIdPart = (int)((externalUserId & 0x7FFFFFF000000000L) >> 36);
    long userIdPart = externalUserId & 0x0000000FFFFFFFFFL;
    
    // You probably DO want to assert this in real code.
    assertNotEquals(1, podIdPart);
    
    // Put the pod and local user ID values into the template.
    String userIdObjectString = String.format(userIdObjectFormat, podIdPart, userIdPart);

    // This test verifies that the template produced the correct string.
    assertEquals(userIdObject.toString(), userIdObjectString);
    
    // A SHA-256 digest function.
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    
    // Hash the filled template bytes, NOTE THAT YOU MUST USE UTF-8 ENCODING.
    byte[] hashDigestBytes = digest.digest(userIdObjectString.getBytes(StandardCharsets.UTF_8));
    
    /* Hash values have the hash type and a length appended to the digest bytes
     * The UserIdObject will always have hash type 1, so we need to append the value 1
     * as the type ID and the value 1 as the length of the type ID (2 extra bytes in total) 
     */
    
    byte[] hashBytes = new byte[hashDigestBytes.length + 2];
    int i=0;
    
    // Copy the digest bytes into the final result
    while(i<hashDigestBytes.length)
      hashBytes[i] = hashDigestBytes[i++];
    
    // Add the hash type ID
    hashBytes[i++] = (byte)1;
    
    // Add the length of the hash type ID
    hashBytes[i++] = (byte)1;
    
    // Encode the final bytes as standard Base64 (not the URL safe variety)
    String hashString = Base64.encodeBase64String(hashBytes);
    
    // Check that the answer was the same.
    assertEquals(userIdHash.toString(), hashString);
  }
}
