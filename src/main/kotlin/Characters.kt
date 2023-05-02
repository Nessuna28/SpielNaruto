open class Characters{

    var name: String
    var taijutsu: Pair<String, Int>
    var ninjutsu1: Pair<String, Int>
    lateinit var ninjutsu2: Pair<String, Int>
    lateinit var ninjutsu3: Pair<String, Int>
    lateinit var extraWeapon: Pair<String, Int>

    var lifePoints = 500
    var chakra = 500
    var baumstamm = 5
    var weapon = "Kunai"

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, ninjutsu2: Pair<String, Int>, ninjutsu3: Pair<String, Int>, extraWeapon: Pair<String, Int>){

        this.name = name
        this.taijutsu = taijutsu
        this.ninjutsu1 = ninjutsu1
        this.ninjutsu2 = ninjutsu2
        this.ninjutsu3 = ninjutsu3
        this.extraWeapon = extraWeapon
    }

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, ninjutsu2: Pair<String, Int>, ninjutsu3: Pair<String, Int>){

        this.name = name
        this.taijutsu = taijutsu
        this.ninjutsu1 = ninjutsu1
        this.ninjutsu2 = ninjutsu2
        this.ninjutsu3 = ninjutsu3
    }

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>, extraWeapon: Pair<String, Int>){

        this.name = name
        this.taijutsu = taijutsu
        this.ninjutsu1 = ninjutsu1
        this.extraWeapon = extraWeapon
    }

    constructor(name: String, taijutsu: Pair<String, Int>, ninjutsu1: Pair<String, Int>){

        this.name = name
        this.taijutsu = taijutsu
        this.ninjutsu1 = ninjutsu1
    }

}