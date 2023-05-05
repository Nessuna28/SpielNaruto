class CharacterWithGenjutsu : Character {

    var genjutsu: Int

    constructor(
        name: String,
        attack: MutableMap<String, Int>,
        ninjutsu: MutableMap<String, Int>,
        weapon: MutableMap<String, Int>,
        genjutsu: Int,
    ) :
            super(name, attack, ninjutsu, weapon) {

        this.genjutsu = genjutsu
    }

    // bei der Funktion werden dem Gegner der Wert des Schadens von dem Genjutsu von seinen Lebenspunkten abgezogen
    // und der Spieler verliert Chakra bei dieser Art Attacke
    // hat er nicht genug Chakra, kann er diese Attacke nicht ausführen
    fun attackWithGenjutsu(enemy: Character) {

        if (enemy == characterComputer) {
            if (chakra > 40) {
                enemy.lifePoints -= this.genjutsu
                lostChakra(40)
                Thread.sleep(2000)
                println("\nDu hast ein Genjutsu angewendet und dein Gegner muss eine Runde aussetzen.")
                selectionUserString = "Genjutsu"
            } else {
                println("\nDu hast nicht genügend Chakra um ein Genjutsu auszuführen. Wähle erneut!")
                showSelection()
            }
        } else if (enemy == characterUser) {
            if (chakra > 40) {
                enemy.lifePoints -= this.genjutsu
                lostChakra(40)
                Thread.sleep(2000)
                println("\nDer Computer hat ein Genjutsu angewendet und du musst eine Runde aussetzen.")
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
                for (attack in attack.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                selectionUserInt = readln().toInt()
                characterUser.attackNormal(selectionUserInt, characterComputer)
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
                characterUser.attackWithNinjutsu(selectionUserInt, characterComputer)
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
                characterUser.attackWithWeapon(selectionUserInt, characterComputer)
                counter = selectionUserInt
            } else if (selectionUserInt == 4) {
                println("Möchtest du ein Genjutsu anwenden?")
                print("Gib ein ja oder nein: ")
                selectionUserString = readln().lowercase()
                if (selectionUserString == "ja") {
                    attackWithGenjutsu(characterComputer)
                    counter = selectionUserInt
                } else {
                    continue
                }
            } else {
                println("Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != selectionUserInt)
    }

}