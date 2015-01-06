class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        // get medical notes for the patient with given ID
        "/patients/$patientID/notes"(
                controller:"MedicalNote",
                action:"notes"
        )
	}
}
