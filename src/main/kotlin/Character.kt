open class Character(
    var name: String,
    var attack: MutableMap<String, Int>
) {

    var lifePoints = 500
    var chakra = 500
    var baumstamm = 5

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

    open fun attack(input: Int, enemy: Character){

            when (input){
                0 -> enemy.lifePoints -= this.attack.values.elementAt(0)
                1 -> enemy.lifePoints -= this.attack.values.elementAt(1)
                2 -> enemy.lifePoints -= this.attack.values.elementAt(2)
            }
    }

}
