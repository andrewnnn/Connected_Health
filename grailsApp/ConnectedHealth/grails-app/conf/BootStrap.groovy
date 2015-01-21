import connectedhealth.Choice
import connectedhealth.JournalEntry
import connectedhealth.MeasurementType
import connectedhealth.MedicalNote
import connectedhealth.Patient
import connectedhealth.Question
import connectedhealth.Questionnaire

class BootStrap {

    def init = { servletContext ->

        Patient p1 = new Patient(firstName: "John", lastName: "Smith", homeAddress: "123 Example St Fakeville", phone: "12340987", contactEmail: "john@smith.ru")
        Patient p2 = new Patient(firstName: "John2", lastName: "Smith2", homeAddress: "123 Example St Fakeville2", phone: "123409872", contactEmail: "john2@smith.ru")
        Patient p3 = new Patient(firstName: "John3", lastName: "Smith3", homeAddress: "123 Example St Fakeville3", phone: "123409873", contactEmail: "john3@smith.ru")
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
        MedicalNote a = new MedicalNote(patient: p1, content: "Begin seven day trial of meloxicam 15mg PO daily. Patient given instruction sheet on back exercises to stretch " +
                "injured muscles and advised to continue work and normal activity as tolerated. Patient advised to keep a pillow between her legs" +
                " when sleeping on side. Patient advised to avoid heavy lifting until back heals. Follow up in 4-6 weeks or sooner if pain worsens or new symptoms develop.", created: new Date(1992,1,1,1,1))
        a.save()

        a = new MedicalNote(patient: p2, content: "Suggest stopping any further doses of imipenem/cilastin. Select other antibiotic to cover gram neg rods until C/S" +
                " is available. Options include: aztreonam, cefipime, ceftazidime, possibly ciprofloxacin.", created: new Date(1540,8,2,6,4,6))
        a.save()

        a = new MedicalNote(patient: p2, content: "Physical therapy, to help you keep proper posture, and deep breathing exercises, to enhance your lung capacity. " +
                "A physical therapist can also help you learn to use heat and cold to help control your pain and stiffness. Heat can help with" +
                " relaxation and pain relief, and cold can help reduce inflammation.", created: new Date(2235,8,2,6,4,6))
        a.save()

        a = new MedicalNote(patient: p3, content: "I am the third note", created: new Date(2066,4,2,33,4,5))
        a.save()

        a = new MedicalNote(patient: p3, content: "Please pay the medical fee", created: new Date(3135,12,2,3,35,5))
        a.save()

        a = new MedicalNote(patient: p3, content: "NO FEE NO LIFE", created: new Date(1234,1,12,3,4,5))
        a.save()

        a = new MedicalNote(patient: p3, content: "FEE! FEE! FEE! FEE! FEE! FEE!", created: new Date(7654,1,12,3,4,5))
        a.save()

        a = new MedicalNote(patient: p3, content: "If you don't pay the fee today, I will ask someone to kill you tomorrow", created: new Date(9999,1,12,3,4,5))
        a.save()

        a = new MedicalNote(patient: p3, content: "good boy", created: new Date(9999,9,9,9,9,9))
        a.save()


        //journal entry
        JournalEntry b = new JournalEntry(patient: p1, content: "This is my small Journal", created: new Date(), updated: new Date())
        b.save()

        b = new JournalEntry(patient: p1, content: "Feeling GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOD today!!!", created: new Date(), updated: new Date())
        b.save()

        b = new JournalEntry(patient: p1, content: "Feeling BAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD today!!!", created: new Date(), updated: new Date())
        b.save()

        b = new JournalEntry(patient: p2, content: "someone gonna kill me tomorrow, I will run away now!!!", created: new Date(), updated: new Date())
        b.save()

        b = new JournalEntry(patient: p3, content: "I am a rich man, I have many houses", created: new Date(), updated: new Date())
        b.save()

        b = new JournalEntry(patient: p3, content: "and many cars!!!", created: new Date(), updated: new Date())
        b.save()

        b = new JournalEntry(patient: p3, content: "and many banks!!!", created: new Date(), updated: new Date())
        b.save()

        b = new JournalEntry(patient: p3, content: "and many boats!!!", created: new Date(), updated: new Date())
        b.save()

        b = new JournalEntry(patient: p3, content: "and many many many many dogs!!!", created: new Date(), updated: new Date())
        b.save()

        Questionnaire questionnaire1 = new Questionnaire(name: "StandardQuestionnaire", description: "General questions for no specific condition.")
        questionnaire1.save()

        Question q1 = new Question(content: "q1", answerFormat: 0, questionnaire: questionnaire1)
        q1.save()
        Question q2 = new Question(content: "q2", answerFormat: 1, questionnaire: questionnaire1)
        q2.save()
        Question q3 = new Question(content: "q3", answerFormat: 2, questionnaire: questionnaire1)
        q3.save()

        Choice q1c1 = new Choice(content: "choice1 for q1", question: q1)
        q1c1.save()
        Choice q1c2 = new Choice(content: "choice2 for q1", question: q1)
        q1c2.save()
        Choice q1c3 = new Choice(content: "choice3 for q1", question: q1)
        q1c3.save()

        Choice q2c1 = new Choice(content: "choice1 for q2", question: q2)
        q2c1.save()
        Choice q2c2 = new Choice(content: "choice2 for q2", question: q2)
        q2c2.save()
        Choice q2c3 = new Choice(content: "choice3 for q2", question: q2)
        q2c3.save()

        Questionnaire questionnaire2 = new Questionnaire(name: "Physical Activity Questionnaire", description: "Questions about recent physical activity.")
        questionnaire2.save()

        Question q4 = new Question(content: "q4", answerFormat: 1, questionnaire: questionnaire2)
        q4.save()

        for (int i = 1; i <= 10; i++) {
            Choice q4choice = new Choice(content: "choice" + i + " for q4", question: q4);
            q4choice.save();
        }

        Question q5 = new Question(content: "q5", answerFormat: 0, questionnaire: questionnaire2)
        q5.save()

        for (int i = 1; i <= 5; i++) {
            Choice q5choice = new Choice(content: "choice" + i + " for q5", question: q5);
            q5choice.save();
        }

        Question q6 = new Question(content: "q6", answerFormat: 0, questionnaire: questionnaire2)
        q6.save()

        for (int i = 1; i <= 5; i++) {
            Choice q6choice = new Choice(content: "choice" + i + " for q6", question: q6);
            q6choice.save();
        }

        Question q7 = new Question(content: "q7", answerFormat: 0, questionnaire: questionnaire2)
        q7.save()

        for (int i = 1; i <= 5; i++) {
            Choice q7choice = new Choice(content: "choice" + i + " for q7", question: q7);
            q7choice.save();
        }




    }
    def destroy = {
    }
}
