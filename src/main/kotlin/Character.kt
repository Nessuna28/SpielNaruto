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
    fun lostLifePoints(value: Int){

        lifePoints -= value
    }

    // Chakra wird um einen bestimmten Wert verringert
    fun lostChakra(value: Int){

        chakra -= value
    }

    // einfaches Ausweichen
    // der Spieler hat nur 5 Mal die Möglichkeit Baumstamm einzusetzen, nach jedem Mal wird einmal abgezogen
    open fun baumstamm(){

        baumstamm --
        println("Du bist ausgewichen!")
    }

    // der Funktion wird der Gegner übergeben und dem werden Lebenspunkte abgezogen um den Wert des Schadens der Attacke Taijutsu des Spielers
    fun attackWithTaijutsu(enemy: Character){

        enemy.lifePoints -= this.taijutsu
    }

    // der Funktion wird der Gegner übergeben und dem werden Lebenspunkte abgezogen um den Wert des Schadens der Attacke Kunai des Spielers
    fun attackWithKunai(enemy: Character){

        enemy.lifePoints -= 15
    }

    fun attackWithNinjutsu(input: Int, attack: String, enemy: Character){

            when (input){
                0 -> enemy.lifePoints -= ninjutsu.values.elementAt(0)
                1 -> enemy.lifePoints -= ninjutsu.values.elementAt(1)
                2 -> enemy.lifePoints -= ninjutsu.values.elementAt(2)
            }
    }

}
