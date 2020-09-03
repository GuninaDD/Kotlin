package ru.geekbrains.gb_kotlin.data

import ru.geekbrains.gb_kotlin.data.model.Note

object Repository {

    internal val notes: List<Note> = listOf(
        Note(
            "Важное событие",
            "Переезд в новую квартиру",
            0xffa9d8f7.toInt()
        ),
        Note(
            "Поход в горы",
            "Список вещей во вложении на почте",
            0xffa9d8f7.toInt()
        ),
        Note(
            "Договор с типографией",
            "Отредактировать макет брошюры и определиться с количеством экземпляров",
            0xffa9d8f7.toInt()
        ),
        Note(
            "Поход в магазин",
            "Надо пополнить запасы туалетной бумаги",
            0xffa9d8f7.toInt()
        ),
        Note(
            "Распрадажа в Sony PlayStation Store",
            "Не во что играть :(",
            0xffa9d8f7.toInt()
        ),
        Note(
            "Новая книга Макса Фрая",
            "Не пропустить старт продаж",
            0xffa9d8f7.toInt()
        )
    )
}