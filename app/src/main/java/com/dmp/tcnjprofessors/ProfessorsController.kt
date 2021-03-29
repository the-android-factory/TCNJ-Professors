package com.dmp.tcnjprofessors

import com.airbnb.epoxy.EpoxyController
import com.dmp.tcnjprofessors.databinding.EpoxyModelProfessorBinding
import com.squareup.picasso.Picasso

class ProfessorsController(
    private val professors: List<GetProfessorsResponse.Professor>
) : EpoxyController() {

    override fun buildModels() {
        professors.sortedBy {
            it.year_joined
        }.forEach { professor ->
            ProfessorEpoxyModel(professor).id(professor.id).addTo(this)
        }
    }

    data class ProfessorEpoxyModel(
        val professor: GetProfessorsResponse.Professor
    ) : ViewBindingKotlinModel<EpoxyModelProfessorBinding>(R.layout.epoxy_model_professor) {

        override fun EpoxyModelProfessorBinding.bind() {
            nameTextView.text = professor.name
            titleTextView.text = professor.info.title

            Picasso.get().load(professor.image).into(imageView)
        }
    }
}