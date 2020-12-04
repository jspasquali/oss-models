package com.symphony.oss.models.object.canon.facade;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.PathHandler;
import com.symphony.oss.canon.runtime.exception.CanonException;
import com.symphony.oss.canon.runtime.exception.NotFoundException;
import com.symphony.oss.canon.runtime.exception.ServerErrorException;
import com.symphony.oss.canon.runtime.http.IRequestAuthenticator;
import com.symphony.oss.canon.runtime.http.IRequestContext;
import com.symphony.oss.canon.runtime.http.ParameterLocation;
import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.object.canon.IPageOfStoredApplicationObject;

/**
 * Path name=PartitionsPartitionHashPage
 *
 * Path					/partitions/{partitionHash}/page
 * Bind Path			partitions/
 */
@Immutable
@SuppressWarnings("unused")
public abstract class StreamingPartitionsPartitionHashPagePathHandler<T> extends PathHandler<T> implements IStreamingPartitionsPartitionHashPagePathHandler<T>
{
  private final String path_;
  
  public StreamingPartitionsPartitionHashPagePathHandler(@Nullable IRequestAuthenticator<T> authenticator)
  {
    this(authenticator, "");
  }
  
  public StreamingPartitionsPartitionHashPagePathHandler(@Nullable IRequestAuthenticator<T> authenticator, String basePath)
  {
    super(authenticator, 1, new String[] {
        basePath + "/object/v1/partitions/",
        "/page"
      }
    );
    
    path_ = basePath + "/object/v1/partitions/{partitionHash}/page";
  }

  @Override
  public String getPath()
  {
    return path_;
  }

  @Override
  public void handle(T auth, IRequestContext context, List<String> pathParams) throws IOException, CanonException
  {
    switch(context.getMethod())
    {
      case Post:
      case Put:
      case Delete:
      case Options:
      case Head:
      case Trace:
        unsupportedMethod(auth, context);
        break;
        
      case Get:
        doGet(auth, context, pathParams);
        break;
        
    }
  }

  private void doGet(T auth, IRequestContext context, List<String> pathParams) throws IOException, CanonException
  {
    Iterator<String> it = pathParams.iterator();
  
    // We have already checked that there are the correct number of parameters
      
    ImmutableByteArray        partitionHashValue = context.asImmutableByteArray("partitionHash", it.next());
    Hash                      partitionHash = null;

    try
    {
      partitionHash = Hash.build(partitionHashValue);
    }
    catch(NullPointerException | IllegalArgumentException e)
    {
      context.error("Parameter \"partitionHash\" has invalid value \"%s\" (%s)", partitionHashValue, e.getMessage());
    }
    
    
  

    String                    sortKeyPrefixValue = context.getParameterAsString("sortKeyPrefix", ParameterLocation.Query, false);
    String                    sortKeyPrefix = null; 
    
    if(sortKeyPrefixValue != null)
    {
      sortKeyPrefix = sortKeyPrefixValue;
    }


    Integer                   limitValue = context.getParameterAsInteger("limit", ParameterLocation.Query, false);
    Integer                   limit = null; 
    
    if(limitValue != null)
    {
      limit = limitValue;
    }


    Boolean                   scanForwardsValue = context.getParameterAsBoolean("scanForwards", ParameterLocation.Query, false);
    Boolean                   scanForwards = null; 
    
    if(scanForwardsValue != null)
    {
      scanForwards = scanForwardsValue;
    }


    String                    afterValue = context.getParameterAsString("after", ParameterLocation.Query, false);
    String                    after = null; 
    
    if(afterValue != null)
    {
      after = afterValue;
    }
    
    Integer                   xSymphonyExternalPodIdValue = context.getParameterAsInteger("X-Symphony-ExternalPodId", ParameterLocation.Header, false);
    PodId                     xSymphonyExternalPodId = null; 
    
    if(xSymphonyExternalPodIdValue != null)
    {
      try
      {
        xSymphonyExternalPodId = PodId.newBuilder().build(xSymphonyExternalPodIdValue);
      }
      catch(NullPointerException | IllegalArgumentException e)
      {
        context.error("Parameter \"xSymphonyExternalPodId\" has invalid value \"%s\" (%s)", xSymphonyExternalPodIdValue, e.getMessage());
      }
    }

  
    if(context.preConditionsAreMet())
    {
    // context.streamHead();
      try
      {
        IPageOfStoredApplicationObject response =
          handleGet(
            auth,
            context.getTrace(),
            sortKeyPrefix,
            limit,
            scanForwards,
            partitionHash,
            after, 
            xSymphonyExternalPodId,
            context.startStreaming()
          );
        
        if(response == null)
        {
          context.stopStreaming();
          throw new NotFoundException();      
        }
        
      }
      catch(CanonException e)
      {
        context.stopStreaming();
        throw e;
      }
      catch(RuntimeException e)
      {
        context.stopStreaming();
        throw new ServerErrorException(e);
      }
    }
  }


}
/*----------------------------------------------------------------------------------------------------
 * End of template template/java/Path/_PathHandler.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */