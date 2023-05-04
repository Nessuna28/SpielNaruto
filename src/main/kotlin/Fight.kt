fun fight() {}

// diese Funktion fragt den Spieler, ob er angreifen oder ausweichen möchte und ruft je nach Antwort die dazugehörigen Funktionen auf
fun selectionAttackUser() {

    if (characterUser.name.isNotEmpty()) {
        print("\nMöchtest du (1) angreifen oder (2) ausweichen? Gib die jeweilige Zahl ein: ")
        inputUserInt = readln().toInt()
        if (inputUserInt == 1) {
            if (characterUser is CharacterWithGenjutsu) {
               characterUser.showSelection()

            } else if (characterUser is CharacterWithMedicalSkills) {
                characterUser.showSelection()
            } else {
                characterUser.showSelection()
            }
        } else if (inputUserInt == 2) {
            characterUser.baumstamm()
        }
    }
}

// diese Funktion wählt ein Angriff oder Verteidigung per Zufall aus je nachdem welchen Charakter der Computer hat
// und speichert die Auswahl dan in der Variablen selectionComputer in der Main
fun attackComputer(){

    val attackList = mutableListOf<String>()

    if (characterComputer is CharacterWithGenjutsu) {
        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
        attackList.add("Genjutsu")
    } else if (characterComputer is CharacterWithMedicalSkills) {
        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
        attackList.add("Heilung")
    } else {
        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
    }

    selectionComputer = attackList.random()
}

