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
        "/patients/$patientID/notes/json"(controller:"MedicalNote",action:"notes")
        "/patients/$patientID/notes/"(controller:"MedicalNote",action:"notesView")

        //journals
        "/patients/$patientID/journal/json"(controller:"JournalEntry"){
            action = [GET:"entries", POST:"newEntry"]
        }
        "/patients/$patientID/journal"(controller:"JournalEntry"){
            action = [GET:"entriesView", POST:"newEntryView"]
        }

        "/patients/$patientID/journal/$journalEntryID/update/json"(controller:"JournalEntry"){
            action = [PUT:"updateEntry", DELETE:"removeEntry"]
        }
        "/patients/$patientID/journal/$journalEntryID/update"(controller:"JournalEntry"){
            action = [PUT:"updateEntryView", DELETE:"removeEntryView"]
        }

        //questions
//        "/questionnaire/$IDorName"(controller:"Questionnaire",action:"get")

    }
}
