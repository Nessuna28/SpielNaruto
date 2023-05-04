class CharacterWithGenjutsu: Character {

    var genjutsu: Int

    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, genjutsu: Int):
            super(name, attack, ninjutsu, weapon){

        this.genjutsu = genjutsu
    }

    // bei der Funktion werden dem Gegner der Wert des Schadens von dem Genjutsu von seinen Lebenspunkten abgezogen
    // und der Spieler verliert Chakra bei dieser Art Attacke
    // hat er nicht genug Chakra, kann er diese Attacke nicht ausführen
    fun attackWithGenjutsu(enemy: Character) {

        if (chakra > 40) {
            enemy.lifePoints -= this.genjutsu
            lostChakra(40)
            Thread.sleep(2000)
            println("\nDu hast ein Genjutsu angewendet und dein Gegner muss eine Runde aussetzen.")
            selectionUser = "Genjutsu"
        } else {
            println("\nDu hast nicht genügend Chakra um ein Genjutsu auszuführen.")
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
            inputUserInt = readln().toInt()

            if (inputUserInt == 1) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in attack.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackNormal(inputUserInt, characterComputer)
                counter = inputUserInt
            } else if (inputUserInt == 2) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in ninjutsu.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackWithNinjutsu(inputUserInt, characterComputer)
                counter = inputUserInt
            } else if (inputUserInt == 3) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in weapon.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackWithWeapon(inputUserInt, characterComputer)
                counter = inputUserInt
            } else if (inputUserInt == 4){
                println("Möchtest du ein Genjutsu anwenden?")
                print("Gib ein ja oder nein: ")
                inputUserString = readln().lowercase()
                if (inputUserString == "ja"){
                    attackWithGenjutsu(characterComputer)
                    counter = inputUserInt
                } else {
                    continue
                }
            } else {
                println("Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != inputUserInt)
    }

}