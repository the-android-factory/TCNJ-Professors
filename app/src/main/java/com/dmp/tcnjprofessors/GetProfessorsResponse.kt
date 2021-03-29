package com.dmp.tcnjprofessors

data class GetProfessorsResponse(
    val professors: List<Professor> = listOf()
) {
    data class Professor(
        val education: List<String> = listOf(),
        val id: String = "",
        val image: String = "",
        val info: Info = Info(),
        val name: String = "",
        val research_interests: List<String>? = null,
        val research_projects: List<String>? = null,
        val year_joined: Int = 0
    ) {
        data class Info(
            val email: String = "",
            val office: String = "",
            val personal_website: String? = null,
            val title: String = ""
        )
    }
}