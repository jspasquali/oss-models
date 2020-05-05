/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import com.symphony.oss.canon.runtime.exception.PermissionDeniedException;
import com.symphony.oss.fugue.trace.ITraceContext;
import com.symphony.oss.fugue.trace.NoOpTraceContext;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;

/**
 * A component which can validate entitlements.
 * 
 * This interface provides a convenience method to request entitlements owned by the caller.
 * 
 * @author Bruce Skingle
 *
 */
public interface IEntitlementValidator extends IBaseEntitlementValidator
{
  /**
   * Ensure that the given subject has all of the given entitlements.
   * 
   * @param subjectUserId     The userId of the user whose entitlements are to be checked.
   * @param trace             A trace context.
   * @param entitlements      One or more entitlements identified by their ID.
   * 
   * @throws PermissionDeniedException If the subject (or the subject's pod) does not have all of the required entitlements.
   */
  void ensureUserHasAllEntitlements(PodAndUserId subjectUserId, ITraceContext trace,
      IServiceEntitlementSpecOrIdProvider... entitlements);
  

  /**
   * Ensure that the given subject has all of the given entitlements.
   * 
   * @param subjectUserId     The userId of the user whose entitlements are to be checked.
   * @param entitlements      One or more entitlements identified by their ID.
   * 
   * @throws PermissionDeniedException If the subject (or the subject's pod) does not have all of the required entitlements.
   */
  default void ensureUserHasAllEntitlements(PodAndUserId subjectUserId,
      IServiceEntitlementSpecOrIdProvider... entitlements)
  {
    ensureUserHasAllEntitlements(subjectUserId, NoOpTraceContext.INSTANCE, entitlements);
  }
}
