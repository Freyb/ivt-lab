package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPrimary;

  @BeforeEach
  public void init(){
    mockPrimary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimary, mockPrimary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockPrimary.fire(geq(1))).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPrimary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockPrimary.fire(geq(1))).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.getTorpedoCount()).thenReturn(10);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPrimary, times(2)).fire(10);
  }

}
