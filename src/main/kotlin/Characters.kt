open class Characters{

    var name: String
    var taijutsu: Int
    var ninjutsu: MutableMap<String, Int>
    lateinit var extraWeapon: Pair<String, Int>

    var lifePoints = 500
    var chakra = 500
    var baumstamm = 5
    var weapon = "Kunai"

    constructor(name: String, taijutsu: Int, ninjutsu: MutableMap<String, Int>){

        this.name = name
        this.taijutsu = taijutsu
        this.ninjutsu = ninjutsu
    }

    constructor(name: String, taijutsu: Int, ninjutsu: MutableMap<String, Int>, extraWeapon: Pair<String, Int>){

        this.name = name
        this.taijutsu = taijutsu
        this.ninjutsu = ninjutsu
        this.extraWeapon = extraWeapon
    }

    fun lostLifePoints(){


}

    fun lostChakra(){


    }

}