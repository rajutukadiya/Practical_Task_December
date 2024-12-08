import android.util.Log
import com.app.task.models.MedicineData
import com.google.gson.JsonObject
import com.google.gson.JsonParser


fun extractData(responseBody: JsonObject?): MutableList<MedicineData>? {
    try {

        val associatedDrugs = mutableListOf<MedicineData>()
        responseBody?.let { jsonObject ->

            val problems = jsonObject["problems"]?.asJsonArray
            problems?.forEach { problemElement ->
                val problems = problemElement.asJsonObject
                val diabetes = problems["Diabetes"]?.asJsonArray
                diabetes?.forEach { diabetesElement ->
                    val diabetesObject = diabetesElement.asJsonObject
                    val medications = diabetesObject["medications"]?.asJsonArray
                    medications?.forEach { medicationElement ->
                        val medication = medicationElement.asJsonObject
                        // Parse the JSON string into a JsonObject
                        val jsonObject = JsonParser().parse(medication.toString()).asJsonObject
                        val medicationsClasses = jsonObject["medicationsClasses"]?.asJsonArray ?: throw Exception("medicationsClasses key is missing or not an array")

                        // Iterate over medicationsClasses
                        for (medicationClass in medicationsClasses) {
                            if (medicationClass.isJsonObject) {
                                val classObject = medicationClass.asJsonObject

                                // Iterate over keys starting with "className"
                                for (key in classObject.keySet()) {
                                    if (key.startsWith("className")) {
                                        val classArray = classObject[key]?.asJsonArray ?: continue

                                        // Iterate over elements in the className array
                                        for (classElement in classArray) {
                                            if (classElement.isJsonObject) {
                                                val associatedDrugObject = classElement.asJsonObject

                                                // Iterate over keys starting with "associatedDrug"
                                                for (drugKey in associatedDrugObject.keySet()) {
                                                    if (drugKey.startsWith("associatedDrug")) {
                                                        val drugArray = associatedDrugObject[drugKey]?.asJsonArray ?: continue

                                                        // Extract drug details
                                                        for (drug in drugArray) {
                                                            if (drug.isJsonObject) {
                                                                val drugDetails = drug.asJsonObject
                                                                val name = drugDetails["name"]?.asString ?: "Unknown"
                                                                val dose = drugDetails["dose"]?.asString ?: "Unknown"
                                                                val strength = drugDetails["strength"]?.asString ?: "Unknown"
                                                                associatedDrugs.add(
                                                                    MedicineData(name,dose,strength)
                                                                )


                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }

        return associatedDrugs
        }



    } catch (e: Exception) {
        println("Error occurred: ${e.message}")
        e.printStackTrace()
    }
    return null
}
