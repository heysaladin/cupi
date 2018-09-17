import 'package:planets/model/Planet.dart';
class PlanetDao {

  static final List<Planet> planets = [
    const Planet(
      id: "1",
      name: "Bedugul",
      location: "Lake & Pura",
      distance: "54.6m Km",
      gravity: "3.711 m/s ",
      description: "Lorem ipsum...",
      image: "assets/img/bedugul.png",
    ),
    const Planet(
      id: "2",
      name: "Gunung Agung",
      location: "Mountain",
      distance: "54.6m Km",
      gravity: "3.711 m/s ",
      description: "Lorem ipsum...",
      image: "assets/img/gunung_agung.png",
    ),
    const Planet(
      id: "3",
      name: "Masjidil Haram",
      location: "Mosque",
      distance: "54.6m Km",
      gravity: "3.711 m/s ",
      description: "Lorem ipsum...",
      image: "assets/img/masjidil_haram.png",
    ),
    const Planet(
      id: "4",
      name: "Gili Trawangan",
      location: "Beach",
      distance: "54.6m Km",
      gravity: "3.711 m/s ",
      description: "Lorem ipsum...",
      image: "assets/img/gili_trawangan.png",
    ),
    const Planet(
      id: "5",
      name: "Hagia Sophia",
      location: "Museum",
      distance: "54.6m Km",
      gravity: "3.711 m/s ",
      description: "Lorem ipsum...",
      image: "assets/img/hagia_sophia.png",
    ),
    // const Planet(
    //   id: "1",
    //   name: "Mars",
    //   location: "Milkyway Galaxy",
    //   distance: "54.6m Km",
    //   gravity: "3.711 m/s ",
    //   description: "Lorem ipsum...",
    //   image: "assets/img/mars.png",
    // ),
    // const Planet(
    //   id: "2",
    //   name: "Neptune",
    //   location: "Milkyway Galaxy",
    //   distance: "54.6m Km",
    //   gravity: "3.711 m/s ",
    //   description: "Lorem ipsum...",
    //   image: "assets/img/neptune.png",
    // ),
    // const Planet(
    //   id: "3",
    //   name: "Moon",
    //   location: "Milkyway Galaxy",
    //   distance: "54.6m Km",
    //   gravity: "3.711 m/s ",
    //   description: "Lorem ipsum...",
    //   image: "assets/img/moon.png",
    // ),
    // const Planet(
    //   id: "4",
    //   name: "Earth",
    //   location: "Milkyway Galaxy",
    //   distance: "54.6m Km",
    //   gravity: "3.711 m/s ",
    //   description: "Lorem ipsum...",
    //   image: "assets/img/earth.png",
    // ),
    // const Planet(
    //   id: "5",
    //   name: "Mercury",
    //   location: "Milkyway Galaxy",
    //   distance: "54.6m Km",
    //   gravity: "3.711 m/s ",
    //   description: "Lorem ipsum...",
    //   image: "assets/img/mercury.png",
    // ),
  ];

  static Planet getPlanetById(id) {
    return planets
        .where((p) => p.id == id)
        .first;
  }
}