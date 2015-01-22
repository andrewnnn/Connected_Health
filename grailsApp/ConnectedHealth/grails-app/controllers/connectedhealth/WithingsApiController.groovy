package connectedhealth

class WithingsApiController {

    def indexView() {
        Patient patient = Patient.findById(params.patientID)
        Set<MeasurementType> measurementTypes = MeasurementType.list(sort: 'name')  // all measurement types

        render(view: "/withingsApi/indexView", model:
                [patient: patient,
                 measurementTypes: measurementTypes])

    }

    def stepsView() {

        render(view: "/withingsApi/stepsView")
    }

    def activitymeasurements() {
        WithingsAPI oauth = new WithingsAPI("3ee15d490209fc40b0163102cfcb34f7451382d27c686b8b24489bdcb66f",
                "f9879f0bcb97c773001e01121de542420014065173d8f915f895efd510fb79",
                "5536764",
                "3d27787c4fdf4d18f60005e06929dbb478249c8c02f6042591b40f9ce101",
                "e71762578dc325bd32ec73b9628b88c519093d65efd80017434c327f88c897");

        if (params.date != null && params.startdate == null && params.enddate == null) {
            render oauth.getActivityMeasurements(params.date);
        } else if (params.date == null && params.startdate != null && params.enddate != null) {
            render oauth.getActivityMeasurements(params.startdate, params.enddate);
        } else if (params.date == null && params.startdate == null && params.enddate == null) {
            render oauth.getActivityMeasurements();
        } else {
            render "Invalid parameters."
        }
    }

    def bodymeasurements() {
        WithingsAPI oauth = new WithingsAPI("3ee15d490209fc40b0163102cfcb34f7451382d27c686b8b24489bdcb66f",
                "f9879f0bcb97c773001e01121de542420014065173d8f915f895efd510fb79",
                "5536764",
                "3d27787c4fdf4d18f60005e06929dbb478249c8c02f6042591b40f9ce101",
                "e71762578dc325bd32ec73b9628b88c519093d65efd80017434c327f88c897");

        if (params.date != null && params.startdate == null && params.enddate == null) {
            render oauth.getBodyMeasurements(params.date);
        } else if (params.date == null && params.startdate != null && params.enddate != null) {
            render oauth.getBodyMeasurements(params.startdate, params.enddate);
        } else if (params.date == null && params.startdate == null && params.enddate == null) {
            render oauth.getBodyMeasurements();
        } else {
            render "Invalid parameters."
        }
    }

}
