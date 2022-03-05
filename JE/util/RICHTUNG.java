package util;

public enum RICHTUNG {
  OBEN,
  UNTEN,
  RECHTS,
  LINKS,;

public static RICHTUNG switchdir(RICHTUNG r) {
  switch (r) {
    case LINKS:
      return RECHTS;
    case OBEN:
      return UNTEN;
    case RECHTS:
      return LINKS;
    case UNTEN:
      return OBEN;
    default:
    return RECHTS;
      
  }
}
}
