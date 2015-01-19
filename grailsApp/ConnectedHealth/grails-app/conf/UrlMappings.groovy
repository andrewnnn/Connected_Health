class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        //routing

        //Patient
        "/patients/"(controller:"Patient",action: "indexView")
        "/patients/$patientID/show"(controller:"Patient",action: "showView")

        "/patients/create"(controller:"Patient"){
            action = [GET:"newView", POST:"createPatient"]
        }

        "/patients/$patientID/edit"(controller:"Patient",action: "editView")
        "/patients/$patientID/"(controller:"Patient"){
            action = [PUT:"updatePatient", DELETE:"deletePatient"]
        }

        //Journal Entry
        "/patients/$patientID/journal"(controller:"JournalEntry", action:"indexView")
        "/patients/$patientID/journal/$journalEntryID"(controller:"JournalEntry", action:"showView")

        "/patients/$patientID/journal/json"(controller:"JournalEntry"){
            action = [GET:"entries", POST:"newEntry"]
        }

        "/patients/$patientID/journal/$journalEntryID/update/json"(controller:"JournalEntry"){
            action = [PUT:"updateEntry", DELETE:"removeEntry"]
        }

        //Medical Note
        "/patients/$patientID/medicalnotes/"(controller:"MedicalNote",action:"indexView")

        "/patients/$patientID/medicalnotes/create"(controller:"MedicalNote"){
            action = [GET:"newView", POST:"createMedicalNote"]
        }

        "/patients/$patientID/medicalnotes/$medicalnoteID/edit"(controller:"MedicalNote",action:"editView")

        "/patients/$patientID/medicalnotes/$medicalnoteID"(controller:"MedicalNote"){
            action = [PUT:"updateMedicalNote", DELETE:"deleteMedicalNote"]
        }

        "/patients/$patientID/medicalnotes/json"(controller:"MedicalNote",action:"notes")

        //Questionnaire
        "/questionnaires/"(controller:"Questionnaire",action:"indexView")
        "/questionnaires/$questionnaireID"(controller:"Questionnaire",action:"showView")

        "/questionnaires/create"(controller:"Questionnaire"){
            action = [GET:"newQuestionnaire", POST:"createQuestionnaire"]
        }

        "/questionnaires/$questionnaireID/edit"(controller:"Questionnaire",action:"editView")

        "/questionnaires/$questionnaireID"(controller:"Questionnaire"){
            action = [PUT:"updateQuestionnaire", DELETE:"deleteQuestionnaire"]
        }

        //Question
        "/questionnaires/$questionnaireID/questions/$questionID"(controller:"Question",action:"showView")

        "/questionnaires/$questionnaireID/questions/create"(controller:"Question"){
            action = [GET:"newQuestion", POST:"createQuestion"]
        }

        "/questionnaires/$questionnaireID/questions/$questionID/edit"(controller:"Question",action:"editView")

        "/questionnaires/$questionnaireID/questions/$questionID"(controller:"Question"){
            action = [PUT:"updateQuestion", DELETE:"removeQuestion"]
        }

        //Choice
        "/questionnaires/$questionnaireID/questions/$questionID/choices"(controller:"Choice",action:"indexView")
        "/questionnaires/$questionnaireID/questions/$questionID/choices/$choiceID"(controller:"Choice",action:"showView")

        "/questionnaires/$questionnaireID/questions/$questionID/choices/create"(controller:"Choice"){
            action = [GET:"newChoice", POST:"createChoice"]
        }

        "/questionnaires/$questionnaireID/questions/$questionID/choices/$choiceID/edit"(controller:"Choice",action:"editView")

        "/questionnaires/$questionnaireID/questions/$questionID/choices/$choiceID"(controller:"Choice"){
            action = [PUT:"updateChoice", DELETE:"removeChoice"]
        }


        //WithingApi
        "/patient/$patientID/measurements"(controller:"MeasurementType", action:"indexView")
        "/patient/$patientID/measurements/steps"(controller:"MeasurementType", action:"stepsView")

    }
}
