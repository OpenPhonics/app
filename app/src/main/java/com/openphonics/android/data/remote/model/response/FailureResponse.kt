

package com.openphonics.android.data.remote.model.response

data class FailureResponse(override val status: State, override val message: String) : Response