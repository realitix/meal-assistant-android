package com.realitix.mealassistant.util

import com.realitix.mealassistant.database.dao.MealAlimentDao
import com.realitix.mealassistant.database.dao.MealReceipeDao
import com.realitix.mealassistant.database.dao.ReceipeStepAlimentDao
import com.realitix.mealassistant.database.dao.ReceipeStepReceipeDao
import com.realitix.mealassistant.database.entity.MealAliment
import com.realitix.mealassistant.database.entity.MealReceipe
import com.realitix.mealassistant.database.entity.ReceipeStepAliment
import com.realitix.mealassistant.database.entity.ReceipeStepReceipe

class RecyclerViewMerger (var text: String, var secondary: String) {
    companion object {
        @JvmStatic
        @JvmName("from1")
        fun from(
            aliments: List<ReceipeStepAliment>,
            receipes: List<ReceipeStepReceipe>
        ): ArrayList<RecyclerViewMerger> {
            val mergedList = ArrayList<RecyclerViewMerger>(aliments.size + receipes.size)
            for (a in aliments) {
                mergedList.add(RecyclerViewMerger(a.aliment.name, a.quantity.toString()+"g"))
            }
            for (r in receipes) {
                mergedList.add(RecyclerViewMerger(r.receipe.name, "test"))
            }

            return mergedList
        }

        @JvmStatic
        @JvmName("from2")
        fun from(
            aliments: List<MealAliment>,
            receipes: List<MealReceipe>
        ): ArrayList<RecyclerViewMerger> {
            val mergedList = ArrayList<RecyclerViewMerger>(aliments.size + receipes.size)
            for (a in aliments) {
                mergedList.add(RecyclerViewMerger(a.aliment.name, a.quantity.toString()+"g"))
            }
            for (r in receipes) {
                mergedList.add(RecyclerViewMerger(r.receipe.name, "test"))
            }

            return mergedList
        }
    }
}