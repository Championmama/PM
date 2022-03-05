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

public static RICHTUNG convertdirectionBack(int facingangle) {
    RICHTUNG _R;
    switch (facingangle) {
        case 0:
            _R = RECHTS;
            break;
        case 1:
            _R = UNTEN;
            break;
        case 2:
            _R = LINKS;
            break;
        case 3:
            _R = OBEN;
            break;

        default:
            _R = RECHTS;
            break;
    }
    return _R;
}
}
