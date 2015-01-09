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

        //medical notes
        "/patients/$patientID/notes"(controller:"MedicalNote",action:"notes")

        //journals
        "/patients/$patientID/journal"(controller:"JournalEntry"){
            action = [GET:"entries", POST:"newEntry"]
        }
        "/patients/$patientID/journal/$journalEntryID/update"(controller:"JournalEntry"){
            action = [PUT:"updateEntry", DELETE:"removeEntry"]
        }

        //questions
//        "/questionnaire/$IDorName"(controller:"Questionnaire",action:"get")

    }
}
