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

package com.symphony.oss.models.fundamental.store;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.fault.TransactionFault;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundmental.canon.ISequence;

public class ValidSequenceHashes
{
  private Set<Hash>  absoluteSequences_ = new HashSet<>();
  private Set<Hash>  currentSequences_ = new HashSet<>();
  private Set<Hash>  hashCurrentSequences_ = new HashSet<>();
  private Set<Hash>  allSequences_ = new HashSet<>();
  
  public ValidSequenceHashes withSequences(IFundamentalDatabaseReadOnly fundamentalDatabase, Collection<Hash> sequenceHashes)
  {
    for(Hash sequenceHash : sequenceHashes)
    {
      try
      {
        ISequence sequence = fundamentalDatabase.fetchCurrent(sequenceHash, ISequence.class);
        
        switch(sequence.getType())
        {
          case CURRENT:
            currentSequences_.add(sequenceHash);
            allSequences_.add(sequenceHash);
            break;
            
          case ABSOLUTE:
            absoluteSequences_.add(sequenceHash);
            allSequences_.add(sequenceHash);
            break;
            
          case HASH_CURRENT:
            hashCurrentSequences_.add(sequenceHash);
            allSequences_.add(sequenceHash);
            break;
            
          default:
            throw new IllegalStateException("Unknown sequence type " + sequence);
        }
      }
      catch (NoSuchObjectException e)
      {
        throw new TransactionFault("Invalid sequence hash " + sequenceHash, e);
      }
    }
    
    return this;
  }
  
  public ValidSequenceHashes withValidCurrentSequence(Hash sequenceHash)
  {
    currentSequences_.add(sequenceHash);
    allSequences_.add(sequenceHash);
    
    return this;
  }
  
  public ValidSequenceHashes withValidHashCurrentSequence(Hash sequenceHash)
  {
    hashCurrentSequences_.add(sequenceHash);
    allSequences_.add(sequenceHash);
    
    return this;
  }
  
  public ValidSequenceHashes withValidAbsoluteSequence(Hash sequenceHash)
  {
    absoluteSequences_.add(sequenceHash);
    allSequences_.add(sequenceHash);
    
    return this;
  }
  
  public ValidSequenceHashes withValidCurrentSequences(Collection<Hash> sequenceHashes)
  {
    currentSequences_.addAll(sequenceHashes);
    allSequences_.addAll(sequenceHashes);
    
    return this;
  }
  
  public ValidSequenceHashes withValidHashCurrentSequences(Collection<Hash> sequenceHashes)
  {
    hashCurrentSequences_.addAll(sequenceHashes);
    allSequences_.addAll(sequenceHashes);
    
    return this;
  }
  
  public ValidSequenceHashes withValidAbsoluteSequences(Collection<Hash> sequenceHashes)
  {
    absoluteSequences_.addAll(sequenceHashes);
    allSequences_.addAll(sequenceHashes);
    
    return this;
  }

  public Set<Hash> getAbsoluteSequences()
  {
    return absoluteSequences_;
  }

  public Set<Hash> getCurrentSequences()
  {
    return currentSequences_;
  }

  public Set<Hash> getHashCurrentSequences()
  {
    return hashCurrentSequences_;
  }

  public Set<Hash> getAllSequences()
  {
    return allSequences_;
  }
}