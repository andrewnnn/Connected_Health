import connectedhealth.Choice
import connectedhealth.JournalEntry
import connectedhealth.MedicalNote
import connectedhealth.Question
import connectedhealth.Questionnaire

class BootStrap {

    def init = { servletContext ->

        //create database seed
        MedicalNote a = new MedicalNote(1,"Begin seven day trial of meloxicam 15mg PO daily. Patient given instruction sheet on back exercises to stretch " +
                "injured muscles and advised to continue work and normal activity as tolerated. Patient advised to keep a pillow between her legs" +
                " when sleeping on side. Patient advised to avoid heavy lifting until back heals. Follow up in 4-6 weeks or sooner if pain worsens or new symptoms develop.", new Date(1992,1,1,1,1))
        a.save()

        a = new MedicalNote(2, "Suggest stopping any further doses of imipenem/cilastin. Select other antibiotic to cover gram neg rods until C/S" +
                " is available. Options include: aztreonam, cefipime, ceftazidime, possibly ciprofloxacin.", new Date(1540,8,2,6,4,6))
        a.save()

        a = new MedicalNote(2, "Physical therapy, to help you keep proper posture, and deep breathing exercises, to enhance your lung capacity. " +
                "A physical therapist can also help you learn to use heat and cold to help control your pain and stiffness. Heat can help with" +
                " relaxation and pain relief, and cold can help reduce inflammation.", new Date(2235,8,2,6,4,6))
        a.save()

        a = new MedicalNote(3, "I am the third note", new Date(2066,4,2,33,4,5))
        a.save()

        a = new MedicalNote(3, "Please pay the medical fee", new Date(3135,12,2,3,35,5))
        a.save()

        a = new MedicalNote(3, "NO FEE NO LIFE", new Date(1234,1,12,3,4,5))
        a.save()

        a = new MedicalNote(3, "FEE! FEE! FEE! FEE! FEE! FEE!", new Date(7654,1,12,3,4,5))
        a.save()

        a = new MedicalNote(3, "If you don't pay the fee today, I will ask someone to kill you tomorrow", new Date(9999,1,12,3,4,5))
        a.save()

        a = new MedicalNote(3, "good boy", new Date(9999,9,9,9,9,9))
        a.save()


        //journal entry
        JournalEntry b = new JournalEntry(1, "This is my small Journal", new Date(), new Date())
        b.save()

        b = new JournalEntry(1, "Feeling GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOD today!!!", new Date(), new Date())
        b.save()

        b = new JournalEntry(1, "Feeling BAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD today!!!", new Date(), new Date())
        b.save()

        b = new JournalEntry(2, "someone gonna kill me tomorrow, I will run away now!!!", new Date(), new Date())
        b.save()

        b = new JournalEntry(3, "I am a rich man, I have many houses", new Date(), new Date())
        b.save()

        b = new JournalEntry(3, "and many cars!!!", new Date(), new Date())
        b.save()

        b = new JournalEntry(3, "and many banks!!!",new Date(), new Date())
        b.save()

        b = new JournalEntry(3, "and many boats!!!", new Date(), new Date())
        b.save()

        b = new JournalEntry(3, "and many many many many dogs!!!", new Date(), new Date())
        b.save()

        Questionnaire questionnaire1 = new Questionnaire(name: "StandardQuestionnaire")
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

        Questionnaire questionnaire2 = new Questionnaire(name: "AnotherQuestoinnaire")
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
