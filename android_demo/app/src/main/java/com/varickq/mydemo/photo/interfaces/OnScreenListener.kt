package com.varickq.mydemo.photo.interfaces

/**
 * Listener to be invoked for screen events.
 */
interface OnScreenListener {

    /**
     * The full screen state has changed.
     */
    fun onFullScreenChanged(fullScreen: Boolean)

    /**
     * A new view has been activated and the previous view de-activated.
     */
    fun onViewActivated()

    /**
     * This view is a candidate for being the next view.
     *
     * This will be called when the view is focused completely on the view immediately before
     * or after this one, so that this view can reset itself if nessecary.
     */
    fun onViewUpNext()

    /**
     * Called when a right-to-left touch move intercept is about to occur.
     *
     * @param origX the raw x coordinate of the initial touch
     * @param origY the raw y coordinate of the initial touch
     * @return `true` if the touch should be intercepted.
     */
    fun onInterceptMoveLeft(origX: Float, origY: Float): Boolean

    /**
     * Called when a left-to-right touch move intercept is about to occur.
     *
     * @param origX the raw x coordinate of the initial touch
     * @param origY the raw y coordinate of the initial touch
     * @return `true` if the touch should be intercepted.
     */
    fun onInterceptMoveRight(origX: Float, origY: Float): Boolean
}