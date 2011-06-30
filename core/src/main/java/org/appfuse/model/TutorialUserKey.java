package org.appfuse.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * This class implement the tutorialUserKey
 *
 * @author Reid
 */
@Embeddable
public class TutorialUserKey implements Serializable {
    private static final long serialVersionUID = 6070037639785281017L;

    private Long userId;                        // required
    private Long tutorialId;                        // required

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public TutorialUserKey() {
    }

    /**
     * Create a new instance and set the tutorial name.
     *
     * @param tutorial name.
     */
    public TutorialUserKey (Long userId, Long tutorialId) {
        this.userId = userId;
        this.tutorialId = tutorialId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTutorialId() {
        return tutorialId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTutorialId(Long tutorialId) {
        this.tutorialId = tutorialId;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TutorialUserKey)) {
            return false;
        }

        final TutorialUserKey tutorialUserKey = (TutorialUserKey) o;

        return ((userId == tutorialUserKey.getUserId()) && (tutorialId == tutorialUserKey.getTutorialId()));

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (userId != null ? userId.hashCode() : tutorialId.hashCode());
    }
}
