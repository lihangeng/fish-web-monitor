package com.yihuacomputer.fish.web.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class OpenSessionInViewInterceptor implements WebRequestInterceptor {

    /**
     * Suffix that gets appended to the <code>SessionFactory</code>
     * <code>toString()</code> representation for the "participate in existing
     * session handling" request attribute.
     * @see #getParticipateAttributeName
     */
    public static final String PARTICIPATE_SUFFIX = ".PARTICIPATE";

    protected final Log logger = LogFactory.getLog(getClass());

    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }


    /**
     * Open a new Hibernate <code>Session</code> according to the settings of this
     * <code>HibernateAccessor</code> and bind it to the thread via the
     * {@link org.springframework.transaction.support.TransactionSynchronizationManager}.
     */
    @Override
    public void preHandle(WebRequest request) throws DataAccessException {
        if (TransactionSynchronizationManager.hasResource(getSessionFactory())) {
            // Do not modify the Session: just mark the request accordingly.
            String participateAttributeName = getParticipateAttributeName();
            Integer count = (Integer) request.getAttribute(participateAttributeName, WebRequest.SCOPE_REQUEST);
            int newCount = (count != null ? count + 1 : 1);
            request.setAttribute(getParticipateAttributeName(), newCount, WebRequest.SCOPE_REQUEST);
        }
        else {
            logger.debug("Opening Hibernate Session in OpenSessionInViewInterceptor");
            Session session = openSession();
            TransactionSynchronizationManager.bindResource(getSessionFactory(), new SessionHolder(session));
        }
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) {
    }

    /**
     * Unbind the Hibernate <code>Session</code> from the thread and close it).
     * @see org.springframework.transaction.support.TransactionSynchronizationManager
     */
    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws DataAccessException {
        String participateAttributeName = getParticipateAttributeName();
        Integer count = (Integer) request.getAttribute(participateAttributeName, WebRequest.SCOPE_REQUEST);
        if (count != null) {
            // Do not modify the Session: just clear the marker.
            if (count > 1) {
                request.setAttribute(participateAttributeName, count - 1, WebRequest.SCOPE_REQUEST);
            }
            else {
                request.removeAttribute(participateAttributeName, WebRequest.SCOPE_REQUEST);
            }
        }
        else {
            SessionHolder sessionHolder =
                    (SessionHolder) TransactionSynchronizationManager.unbindResource(getSessionFactory());
            logger.debug("Closing Hibernate Session in OpenSessionInViewInterceptor");
            SessionFactoryUtils.closeSession(sessionHolder.getSession());
        }
    }

    /**
     * Open a Session for the SessionFactory that this interceptor uses.
     * <p>The default implementation delegates to the
     * <code>SessionFactory.openSession</code> method and
     * sets the <code>Session</code>'s flush mode to "MANUAL".
     * @return the Session to use
     * @throws DataAccessResourceFailureException if the Session could not be created
     * @see org.hibernate.FlushMode#MANUAL
     */
    protected Session openSession() throws DataAccessResourceFailureException {
        try {
            Session session = getSessionFactory().openSession();
            session.setFlushMode(FlushMode.AUTO);
            return session;
        }
        catch (HibernateException ex) {
            throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
        }
    }

    /**
     * Return the name of the request attribute that identifies that a request is
     * already intercepted.
     * <p>The default implementation takes the <code>toString()</code> representation
     * of the <code>SessionFactory</code> instance and appends {@link #PARTICIPATE_SUFFIX}.
     */
    protected String getParticipateAttributeName() {
        return getSessionFactory().toString() + PARTICIPATE_SUFFIX;
    }

}