package lta.amazoning.track;

import android.text.Spannable;

public interface SpinnerTextFormatter<T> {

    Spannable format(T item);
}