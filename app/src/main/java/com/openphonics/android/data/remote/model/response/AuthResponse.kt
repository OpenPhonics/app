package com.openphonics.android.data.remote.model.response

/*
 * Copyright 2020 Shreyas Patil
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



/**
 * Response model used in Authentication API. For e.g. Login/Register.
 */

data class AuthResponse(
    override val status: State,
    override val message: String,
    val token: String? = null
) : Response {

    companion object {

        fun failed(message: String) = AuthResponse(
            State.FAILED,
            message
        )

        fun unauthorized(message: String) = AuthResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(token: String, message: String) = AuthResponse(
            State.SUCCESS,
            message,
            token
        )
    }
}

data class Student(
    val name: String,
    val classCode: String,
    val native: String,
    val progress: List<LanguageProgress>,
)
data class Classroom(
    val className: String,
    val classCode: String,
    val students: List<Student>,

    )

data class ClassroomResponse(
    override val status: State,
    override val message: String,
    val classroom: List<Classroom> = emptyList()
    )  : Response {
    companion object {
        fun unauthorized(message: String) = ClassroomResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(classroom: List<Classroom>) = ClassroomResponse(
            State.SUCCESS,
            "Task successful",
            classroom
        )
        fun success(classroom: Classroom) = ClassroomResponse(
            State.SUCCESS,
            "Task successful",
            listOf(classroom)
        )

        fun failed(message: String) = ClassroomResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = ClassroomResponse(
            State.NOT_FOUND,
            message
        )
    }
}