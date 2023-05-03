open class Character{

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

    // Lebenspunkte werden um einen bestimmten Wert verringert
    fun lostLifePoints(){

    }

    // Chakra wird um einen bestimmten Wert verringert
    fun lostChakra(value: Int){

        chakra -= value
    }

    // einfaches Ausweichen
    // der Spieler hat nur 5 Mal die Möglichkeit Baumstamm einzusetzen, nach jedem Mal wird einmal abgezogen
    open fun baumstamm(){

        println("Du bist ausgewichen!")
        baumstamm --
    }

}