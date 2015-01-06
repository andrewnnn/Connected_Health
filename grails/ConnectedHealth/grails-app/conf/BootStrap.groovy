import connectedhealth.MedicalNote

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

    }
    def destroy = {
    }
}
