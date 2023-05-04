fun fight() {
}


fun selectionAttackUser() {

    if (characterUser.name.isNotEmpty()) {
        print("\nMöchtest du (1)angreifen oder (2)ausweichen? Gib die Zahl ein: ")
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

