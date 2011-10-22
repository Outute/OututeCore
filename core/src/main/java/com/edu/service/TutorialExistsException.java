package com.edu.service;


/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 * @author Reid
 */
public class TutorialExistsException extends Exception {
    private static final long serialVersionUID = 4050482305178810164L;

    /**
     * Constructor for TutorialExistsException.
     *
     * @param message exception message
     */
    public TutorialExistsException(final String message) {
        super(message);
    }
}
