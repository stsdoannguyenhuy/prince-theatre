package com.interview.princethreatre.util

import android.util.Log

class LogUtil {
    companion object {
        private val allowLog = true
        private val TAG_NAME = "PrinceTheatre_Application"
        fun i(text: String) {
            if (allowLog)
                Log.i(TAG_NAME, text)
        }

        fun d(text: String) {
            if (allowLog)
                Log.d(TAG_NAME, text)
        }

        fun w(text: String) {
            if (allowLog)
                Log.w(TAG_NAME, text)
        }

        fun v(text: String) {
            if (allowLog)
                Log.v(TAG_NAME, text)
        }

        fun e(text: String) {
            if (allowLog)
                Log.e(TAG_NAME, text)
        }

        fun e(text: String, err: Throwable) {
            Log.e(TAG_NAME, text, err)
        }
    }

}