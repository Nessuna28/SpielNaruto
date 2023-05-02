class CharacterWithGenjutsu: Characters {

    var genjutsu: Pair<String, Int>

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, genjutsu: Pair<String, Int>):
            super(name, taijutsu, ninjutsu1){

        this.genjutsu = genjutsu
    }

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, extraWeapon: Pair<String, Int>, genjutsu: Pair<String, Int>):
            super(name, taijutsu, ninjutsu1, extraWeapon){

        this.genjutsu = genjutsu
    }

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, ninjutsu2: Pair<String, Int>, ninjutsu3: Pair<String, Int>, extraWeapon: Pair<String, Int>, genjutsu: Pair<String, Int>):
            super(name, taijutsu, ninjutsu1, ninjutsu2, ninjutsu3, extraWeapon){

        this.genjutsu = genjutsu
    }
}