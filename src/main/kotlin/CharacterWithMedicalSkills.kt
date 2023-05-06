class CharacterWithMedicalSkills: Character {

    var medicalSkills: Int

    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, medicalSkills: Int):
            super(name, attack, ninjutsu, weapon){

        this.medicalSkills = medicalSkills
    }

    // diese Funktion ermöglicht es dem Spieler sich zu heilen in dem seinen lifePoints den Wert des Skills dazu gerechnet werden
    // aber mehr als 500 (Anfangswert) gehen nicht
    // diese Fähigkeit verbraucht Chakra um den Wert des Skills
    fun heal(input: String) {

        if (input == selectionUserString) {
            if (this.chakra >= medicalSkills) {
                if (lifePoints < lifePointStart) {
                    this.lifePoints += medicalSkills
                    lostChakra(medicalSkills)
                    if (lifePoints > lifePointStart) {
                        lifePoints = lifePointStart
                    }
                    coloredBar()
                    println("\n\uD83D\uDCAA\uD83C\uDFFC ${favoriteColorUser} Du wurdest geheilt! $reset")
                } else if (this.lifePoints == lifePointStart) {
                    println("\n\uD83E\uDD14 Deine Lebenspunkte sind voll. Diese Auswahl war unnötig.")
                }
                selectionUserString = "Heilung"
            } else {
                println("\n\uD83D\uDE23 Du hast nicht genügend Chakra um dich zu heilen. Wähle erneut!")
                showSelection()
            }
        } else if (input == selectionComputer) {
            if (this.chakra >= medicalSkills) {
                if (lifePoints < lifePointStart) {
                    this.lifePoints += medicalSkills
                    lostChakra(medicalSkills)
                    if (lifePoints > lifePointStart) {
                        lifePoints = lifePointStart
                    }
                    println("\n${blue}Der Computer wurde geheilt! $reset")
                }
            } else {
                attackComputer()
            }
        }
    }

    // in der Funktion wird ein farbiger Balken generiert für die Funktion heal
    fun coloredBar(){

        val life = lifePoints / 50
        val to = life + (medicalSkills / 50)
        val end = lifePointStart / 50

        var coloredBar = StringBuilder()
        coloredBar.append("$greyBackground| | | | | | | | | | ")

        print(coloredBar)

        for (point in 0..life){
            if (point < end){
                coloredBar.append("$blueBackground | |$reset")
                print("\r$coloredBar")
                Thread.sleep(800)
            }

            if (point > to){
                coloredBar.append("$greenBackground | |$reset")
                print("\r$coloredBar")
                Thread.sleep(800)

            }
        }
    }

    // die Funktion aus Character um die Möglichkeit der Heilung erweitert
    override fun showSelection() {

        var counter = 0

        do {
            println(
                """
                    
            Womit möchtest du angreifen?
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
            4 für Heilung
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
                characterUser.attackNormal(selectionUserInt)
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
            } else if (selectionUserInt == 4){
                heal(selectionUserString)
                counter = selectionUserInt
            } else {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != selectionUserInt)
    }

}