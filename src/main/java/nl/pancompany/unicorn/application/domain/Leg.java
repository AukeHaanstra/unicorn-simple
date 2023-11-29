package nl.pancompany.unicorn.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Leg {

        private final LegPosition legPosition;

        @Setter
        private Color color;

        @Setter
        private LegSize legSize;

        @RequiredArgsConstructor
        @Getter
        public enum LegPosition {
            FRONT_LEFT("front left"), FRONT_RIGHT("front right"), BACK_LEFT("back left"), BACK_RIGHT("back right");

            final String description;
        }

        @RequiredArgsConstructor
        @Getter
        public enum LegSize {
                LARGE("large"), SMALL("small");

                final String description;
        }
}
