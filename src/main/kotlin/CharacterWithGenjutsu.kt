class CharacterWithGenjutsu : Character {

    var genjutsu: Int

    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, genjutsu: Int, ) :
            super(name, attack, ninjutsu, weapon) {

        this.genjutsu = genjutsu
    }

    // bei der Funktion wird dem Gegner der Wert des Schadens von dem Genjutsu von seinen Lebenspunkten abgezogen
    // und der Spieler verliert Chakra um den Wert der Attacke
    // hat er nicht genug Chakra, kann er diese Attacke nicht ausführen
    fun attackWithGenjutsu(enemy: Character) {


        if (chakra > genjutsu) {
            enemy.lifePoints -= this.genjutsu
            lostChakra(genjutsu)
            if (enemy == characterComputer) {
                selectionUserString = "Genjutsu"
            }
        } else {
            if (enemy == characterComputer) {
                println("\n\uD83D\uDE23 Du hast nicht genügend Chakra um ein Genjutsu auszuführen. Wähle erneut!")
                showSelection()
            } else {
                attackComputer()
            }

        }
    }

    // die Funktion aus Character um die Möglichkeit ein Genjutsu anzuwenden erweitert
    override fun showSelection() {

            var counter = 0

            do {
                println(
                    """
                    
            Womit möchtest du angreifen?
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
            4 für Genjutsu
        """.trimIndent()
                )
                print("Gib die jeweilige Zahl ein: ")
                selectionUserInt = readln().toInt()

                if (selectionUserInt == 1) {
                    println("\nDas hast du zur Auswahl:")
                    var index = 1
                    for (attack in taijutsu.keys) {
                        println("$index für $attack")
                        index++
                    }
                    print("Triff deine Auswahl per Zahl: ")
                    selectionUserInt = readln().toInt()
                    characterUser.attackWithTaijutsu(selectionUserInt)
                    counter = selectionUserInt
                } else if (selectionUserInt == 2) {
                    println("\nDas hast du zur Auswahl:")
                    var index = 1
                    for (attack in ninjutsu.keys) {
                        println("$index für $attack")
                        index++
                    }
                    print("Triff deine Auswahl per Zahl: ")
                    selectionUserInt = readln().toInt()
                    characterUser.attackWithNinjutsu(selectionUserString, selectionUserInt)
                    counter = selectionUserInt
                } else if (selectionUserInt == 3) {
                    println("\nDas hast du zur Auswahl:")
                    var index = 1
                    for (attack in weapon.keys) {
                        println("$index für $attack")
                        index++
                    }
                    print("Triff deine Auswahl per Zahl: ")
                    selectionUserInt = readln().toInt()
                    characterUser.attackWithWeapon(selectionUserInt)
                    counter = selectionUserInt
                } else if (selectionUserInt == 4) {
                        attackWithGenjutsu(characterComputer)
                        counter = selectionUserInt
                } else {
                    println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                    counter = 0
                }
            } while (counter != selectionUserInt)
        }
}