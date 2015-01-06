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
        "/patient/$patientID/notes"(controller:"MedicalNote",action:"notes")

        //journals
        "/patient/$patientID/journal"(controller:"JournalEntry",action:"showEntry")
        "/patient/$patientID/journal/create"(controller:"JournalEntry",action:"create")
        "/patient/$patientID/journal/$journalEntryID/update"(controller:"JournalEntry",action:"update")
        "/patient/$patientID/journal/$journalEntryID/remove"(controller:"JournalEntry",action:"remove")

        //questions
        "/questionnaire/$IDorName"(controller:"Questionnaire",action:"get")

    }
}
