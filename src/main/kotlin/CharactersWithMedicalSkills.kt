class CharactersWithMedicalSkills: Characters {

    var medicalSkills: Boolean

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, medicalSkills: Pair<String, Int>):
            super(name, taijutsu, ninjutsu1){

        this.medicalSkills = true
    }

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, ninjutsu2: Pair<String, Int>, ninjutsu3: Pair<String, Int>, medicalSkills: Boolean):
            super(name, taijutsu, ninjutsu1, ninjutsu2, ninjutsu3){

        this.name = name
        this.taijutsu = taijutsu
        this.ninjutsu1 = ninjutsu1
        this.ninjutsu2 = ninjutsu2
        this.ninjutsu3 = ninjutsu3
        this.medicalSkills = medicalSkills
    }

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, extraWeapon: Pair<String, Int>, medicalSkills: Boolean):
            super(name, taijutsu, ninjutsu1, extraWeapon){

        this.medicalSkills = true
    }

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, ninjutsu2: Pair<String, Int>, ninjutsu3: Pair<String, Int>, extraWeapon: Pair<String, Int>, medicalSkills: Boolean):
            super(name, taijutsu, ninjutsu1, ninjutsu2, ninjutsu3, extraWeapon){

        this.medicalSkills = true
    }
}