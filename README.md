# 목표
    @Test
     @DisplayName("50 - 30 == 20")
     public void test6() {
         assertThat(Calc.run("50 - 30")).isEqualTo(20);
     }