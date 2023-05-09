class CharacterWithBijuu: Character {

    var bijuu: Pair<String, Int>


    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, bijuu: Pair<String, Int>):
            super(name, attack, ninjutsu, weapon) {

                this.bijuu = bijuu
            }

    fun attackWithBijuu(enemy: Character) {

        val bijuuName = this.bijuu.first
        val bijuuValue = this.bijuu.second

        if (chakra > bijuuValue) {
            enemy.lifePoints -= bijuuValue
            lostChakra(bijuuValue)
            if (enemy == characterComputer) {
                selectionUserString = bijuuName
            }
        } else {
            if (enemy == characterComputer) {
                println("\n\uD83D\uDE23 Du hast nicht genügend Chakra um dein Bijuu zu erwecken. Wähle erneut!")
                showSelection()
            } else {
                attackComputer()
            }

        }
    }

    // die Funktion aus Character um die Möglichkeit ein Bijuu oder Kami zu erwecken erweitert
    override fun showSelection() {

        val bijuuName = this.bijuu.first
        var counter = 0

        do {
                println(
                    """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
            4 für $bijuuName erwecken $reset
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
                    attackWithBijuu(characterComputer)
                    counter = selectionUserInt
                } else {
                    println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                    counter = 0
                }
        } while (counter != selectionUserInt)
    }
}