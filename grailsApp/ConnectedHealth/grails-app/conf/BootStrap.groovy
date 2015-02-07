import connectedhealth.Choice
import connectedhealth.JournalEntry
import connectedhealth.MeasurementType
import connectedhealth.MedicalNote
import connectedhealth.Patient
import connectedhealth.Question
import connectedhealth.Questionnaire
import connectedhealth.SingleSelectionAnswer
import connectedhealth.Submission
import org.joda.time.DateTime

class BootStrap {

    def init = { servletContext ->

        Patient p1 = new Patient(firstName: "Wayland", lastName: "Johannes", homeAddress: "123 Example St Fakeville", phone: "12340987", contactEmail: "wayland@johannes.com")
        Patient p2 = new Patient(firstName: "Julian", lastName: "Leandro", homeAddress: "5 Example St Fakeland", phone: "22221111", contactEmail: "julian@leandro.com")
        Patient p3 = new Patient(firstName: "John", lastName: "Smith", homeAddress: "99 Example St Faketown", phone: "64644646", contactEmail: "john3@smith.com")
        p1.save()
        p2.save()
        p3.save()

        MeasurementType mt1 = new MeasurementType(name: "Blood Pressure", description: "From some device.")
        mt1.save()
        MeasurementType mt2 = new MeasurementType(name: "Steps", description: "Number of steps taken.")
        mt2.save()
        MeasurementType mt3 = new MeasurementType(name: "Weight", description: "From Withings scale.")
        mt3.save()

        p1.addToMeasurementTypes(mt1).addToMeasurementTypes(mt2).addToMeasurementTypes(mt3).save()
        p2.addToMeasurementTypes(mt1).addToMeasurementTypes(mt2).addToMeasurementTypes(mt3).save()
        p3.addToMeasurementTypes(mt1).addToMeasurementTypes(mt2).addToMeasurementTypes(mt3).save()

        //create database seed
        new MedicalNote(patient: p1, content: "Use the instruction sheet to stretch injured muscles.",
                created: new DateTime(2015,1,26,1,1,13).toDate()).save()

        new MedicalNote(patient: p1, content: "Avoid heavy lifting for the next 2 weeks.",
                created: new DateTime(2015,1,25,1,1,8).toDate()).save()

        new MedicalNote(patient: p2, content: "Your weight has improved since you started doing regular exercise.",
                created: new DateTime(2015,1,25,1,1,14).toDate()).save()

        new MedicalNote(patient: p2, content: "Remember to stretch before exercising.",
                created: new DateTime(2015,1,21,1,1,25).toDate()).save()

        new MedicalNote(patient: p2, content: "Continue your physical therapy to help you keep proper posture, and deep breathing exercises to enhance your lung capacity.",
                created: new DateTime(2015,1,20,1,1,20).toDate()).save()

        new MedicalNote(patient: p2, content: "Your weight has seen an increase in recent weeks, please stick to the diet prescribed or come into the clinic for more consultation.",
                created: new DateTime(2015,1,18,6,4,6).toDate()).save()

        new MedicalNote(patient: p3, content: "Please sleep earlier.",
                created: new DateTime(2015,1,25,23,4,5).toDate()).save()

        new MedicalNote(patient: p3, content: "Advise you to stick to 3-4 small meals a day to keep weight levels stable.",
                created: new DateTime(2015,1,24,3,35,5).toDate()).save()

        new MedicalNote(patient: p3, content: "Remember to take medicine in the morning, afternoon and at night.",
                created: new DateTime(2015,1,23,3,4,5).toDate()).save()

        new MedicalNote(patient: p3, content: "Taking your daily medication after meals is recommended.",
                created: new DateTime(2015,1,22,3,4,5).toDate()).save()

        new MedicalNote(patient: p3, content: "Advise you to fit in a minimum of 30 mins of physical exercise per day.",
                created: new DateTime(2015,1,21,3,4,5).toDate()).save()

        new MedicalNote(patient: p3, content: "Make sure you have a decent stretch before commencing physical exercise.",
                created: new DateTime(2015,1,20,9,9,9).toDate()).save()

        //journal entry
        new JournalEntry(patient: p1, content: "I started going for walks in the afternoon.",
                created: new DateTime(2015,1,25,19,30,30).toDate(), updated: new DateTime(2015,1,25,19,30,30).toDate()).save()

        new JournalEntry(patient: p1, content: "My legs are a bit sore after I walked quickly yesterday. I think it's because I didn't stretch before walking.",
                created: new DateTime(2015,1,25,18,30,30).toDate(), updated: new DateTime(2015,1,25,18,30,30).toDate()).save()

        new JournalEntry(patient: p1, content: "I'm getting used to writing daily journal entries.",
                created: new DateTime(2015,1,24,13,10,5).toDate(), updated: new DateTime(2015,1,24,13,10,5).toDate()).save()

        new JournalEntry(patient: p1, content: "I forgot to answer the questionnaires yesterday.",
                created: new DateTime(2015,1,22,10,10,9).toDate(), updated: new DateTime(2015,1,22,10,10,9).toDate()).save()

        new JournalEntry(patient: p2, content: "I've felt more energetic since I started taking my new medication.",
                created: new DateTime(2015,1,25,12,11,10).toDate(), updated: new DateTime(2015,1,25,12,11,10).toDate()).save()

        new JournalEntry(patient: p3, content: "Today was a good day, I reached a new weekly record for steps taken.",
                created: new DateTime(2015,1,25,11,11,10).toDate(), updated: new DateTime(2015,1,25,11,11,10).toDate()).save()

        new JournalEntry(patient: p3, content: "I can already see the difference in weight from striving to one up my previous steps from the day before.",
                created: new DateTime(2015,1,25,10,10,10).toDate(), updated: new DateTime(2015,1,25,10,10,10).toDate()).save()

        new JournalEntry(patient: p3, content: "After a few weeks, I am really getting the hang of this system and my carers are also seeing improvements. I am really happy!",
                created: new DateTime(2015,1,24,12,10,5).toDate(), updated: new DateTime(2015,1,24,12,10,5).toDate()).save()

        new JournalEntry(patient: p3, content: "My mood has been better in recent weeks. I think this can be attributed to the clean diet and daily exercise, which I'm really enjoying.",
                created: new DateTime(2015,1,23,12,10,5).toDate(), updated: new DateTime(2015,1,23,12,10,5).toDate()).save()

        new JournalEntry(patient: p3, content: "Yesterday I received my first remote check up from a nurse. It was nice to have an expert be able to check up and discuss my current health.",
                created: new DateTime(2015,1,22,10,10,5).toDate(), updated: new DateTime(2015,1,22,10,10,5).toDate()).save()

        Questionnaire questionnaire1 = new Questionnaire(name: "General Questionnaire", description: "Questions about general wellbeing.")
        questionnaire1.save()

        Question q1q1 = new Question(content: "Do you feel tired today?", answerFormat: 0, questionnaire: questionnaire1)
        q1q1.save()
        Choice c1 = new Choice(content: "Yes", question: q1q1).save()
        new Choice(content: "A bit", question: q1q1).save()
        new Choice(content: "No", question: q1q1).save()

        Question q1q2 = new Question(content: "How good was your sleep last night?", answerFormat: 0, questionnaire: questionnaire1)
        q1q2.save()
        new Choice(content: "Very good", question: q1q2).save()
        new Choice(content: "Good", question: q1q2).save()
        new Choice(content: "Ok", question: q1q2).save()
        new Choice(content: "Bad", question: q1q2).save()
        new Choice(content: "Very bad", question: q1q2).save()

        Question q1q3 = new Question(content: "Did you take your medication today?", answerFormat: 0, questionnaire: questionnaire1)
        q1q3.save()
        new Choice(content: "Yes", question: q1q3).save()
        new Choice(content: "No", question: q1q3).save()

        Question q1q4 = new Question(content: "Do you have difficulty with any of the following activities?", answerFormat: 1, questionnaire: questionnaire1)
        q1q4.save()
        new Choice(content: "Standing up after sitting", question: q1q4).save()
        new Choice(content: "Sitting down up after standing", question: q1q4).save()
        new Choice(content: "Walking", question: q1q4).save()

        Questionnaire questionnaire2 = new Questionnaire(name: "Physical Activity Questionnaire", description: "Questions about recent physical activity.")
        questionnaire2.save()

        Question q2q1 = new Question(content: "Did you do 30 minutes of exercise yesterday?", answerFormat: 0, questionnaire: questionnaire2)
        q2q1.save()
        new Choice(content: "Yes", question: q2q1).save()
        new Choice(content: "No", question: q2q1).save()

        Question q2q2 = new Question(content: "Do you have difficulty with any of the following activities?", answerFormat: 1, questionnaire: questionnaire2)
        q2q2.save()
        new Choice(content: "Standing up after sitting", question: q2q2).save()
        new Choice(content: "Sitting down after standing", question: q2q2).save()
        new Choice(content: "Concentrating on what you are doing", question: q2q2).save()
        new Choice(content: "Turning your head", question: q2q2).save()
        new Choice(content: "Walking", question: q2q2).save()
        new Choice(content: "Writing", question: q2q2).save()

        // try submission
/*        Submission s = new Submission(created: (new Date()), patient: p1, questionnaire: questionnaire1)
        s.save()
        SingleSelectionAnswer ssa = new SingleSelectionAnswer(submission: s, choice: c1, question: q1q1)
        ssa.save()
        SingleSelectionAnswer ssa2 = new SingleSelectionAnswer(submission: s, choice: c1, question: q1q1)
        ssa2.save()
        SingleSelectionAnswer ssa3 = new SingleSelectionAnswer(submission: s, choice: c1, question: q1q1)
        ssa3.save()*/
    }
    def destroy = {
    }
}
