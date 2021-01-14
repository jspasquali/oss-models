/**
 * GENERATED CODE - DO NOT EDIT OR CHECK IN TO SOURCE CODE CONTROL
 *
 * Copyright 2020 Symphony Communication Services, LLC.
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
 * Generated from
 *    Input source         file:/Users/geremia.longobardo/workspace/oss-models/authc-model/src/main/resources/canon/authc.json
 *    Template groupId     org.symphonyoss.s2.canon
 *           artifactId    canon-template-java
 *    Template name        template/java/Path/_PathHandler.java.ftl
 *    At                   2020-12-16 14:53:52 CET
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

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

/**
 * Path name=Version
 *
 * Path					/version
 * Bind Path			version
 */
@Immutable
@SuppressWarnings("unused")
public abstract class ServiceVersionPathHandler<T> extends PathHandler<T> implements IServiceVersionPathHandler<T>
{
  private final String path_;
  
  public ServiceVersionPathHandler(@Nullable IRequestAuthenticator<T> authenticator, String basePath)
  {
    super(authenticator, 0, new String[] {
        basePath + "/v1/version"
      }
    );
    
    path_ = basePath + "/v1/version";
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
        context.setContentType("text/plain");
        break;
        
    }
  }

  private void doGet(T auth, IRequestContext context, List<String> pathParams) throws IOException, CanonException
  {
    Iterator<String> it = pathParams.iterator();
    
  
    if(context.preConditionsAreMet())
    {
      try
      {
        IPlainText response =
          handleGet(
            auth,
            context.getTrace()
  
          );
        if(response == null)
        {
          throw new ServerErrorException("Required return value is null");        
        }
        else
        {
          context.sendOKResponse(response);
        }
      }
      catch(NotFoundException e)
      {
        throw new ServerErrorException("Response is required but non returned.", e);
      }
      catch(CanonException e)
      {
        throw e;
      }
      catch(RuntimeException e)
      {
        throw new ServerErrorException(e);
      }
    }
  }


}
/*----------------------------------------------------------------------------------------------------
 * End of template template/java/Path/_PathHandler.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */