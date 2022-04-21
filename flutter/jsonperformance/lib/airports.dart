// To parse this JSON data, do
//
//     final airport = airportFromJson(jsonString);

import 'dart:convert';

List<Airport> airportFromJson(String str) =>
    List<Airport>.from(json.decode(str).map((x) => Airport.fromJson(x)));

String airportToJson(List<Airport> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class Airport {
  Airport({
    required this.name,
    required this.iata,
    required this.icao,
    required this.coordinates,
    required this.runways,
  });

  final String name;
  final String iata;
  final String icao;
  final List<double> coordinates;
  final List<Runway> runways;

  factory Airport.fromJson(Map<String, dynamic> json) => Airport(
        name: json["name"],
        iata: json["iata"],
        icao: json["icao"],
        coordinates:
            List<double>.from(json["coordinates"].map((x) => x.toDouble())),
        runways:
            List<Runway>.from(json["runways"].map((x) => Runway.fromJson(x))),
      );

  Map<String, dynamic> toJson() => {
        "name": name,
        "iata": iata,
        "icao": icao,
        "coordinates": List<dynamic>.from(coordinates.map((x) => x)),
        "runways": List<dynamic>.from(runways.map((x) => x.toJson())),
      };
}

class Runway {
  Runway({
    required this.direction,
    required this.distance,
    required this.surface,
  });

  final String direction;
  final int distance;
  final Surface? surface;

  factory Runway.fromJson(Map<String, dynamic> json) => Runway(
        direction: json["direction"],
        distance: json["distance"],
        surface: surfaceValues.map[json["surface"]],
      );

  Map<String, dynamic> toJson() => {
        "direction": direction,
        "distance": distance,
        "surface": surface == null ? null : surfaceValues.reverse[surface],
      };
}

enum Surface { FLEXIBLE, UNPAVED, RIGID, OTHER, GRAVEL, SEALED }

final surfaceValues = EnumValues({
  "flexible": Surface.FLEXIBLE,
  "gravel": Surface.GRAVEL,
  "other": Surface.OTHER,
  "rigid": Surface.RIGID,
  "sealed": Surface.SEALED,
  "unpaved": Surface.UNPAVED
}, {
  Surface.FLEXIBLE: "flexible",
  Surface.GRAVEL: "gravel",
  Surface.OTHER: "other",
  Surface.RIGID: "rigid",
  Surface.SEALED: "sealed",
  Surface.UNPAVED: "unpaved"
});

class EnumValues<T> {
  Map<String, T> map;
  Map<T, String> reverseMap;

  EnumValues(
    this.map,
    this.reverseMap,
  );

  Map<T, String> get reverse {
    reverseMap;
    return reverseMap;
  }
}
