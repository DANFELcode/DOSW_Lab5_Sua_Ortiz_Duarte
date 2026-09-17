## Integrantes

* Daniel Felipe Sua

* David Felipe Ortiz

* Juan Pablo Duarte

## Evidencia TDD - Daniel Felipe

### A. Registro de drones (addDrone)

#### Ciclo TDD - registrar un dron válido

**RED:**

![RED](skyrescue-tdd/docs/evidence/adddrone-valid-red.png)

**GREEN:** 

![GREEN](skyrescue-tdd/docs/evidence/adddrone-valid-green.png)


### B. Asignación de misiones (assignMission)

#### Ciclo TDD - operador inexistente

**RED:** 

![RED](skyrescue-tdd/docs/evidence/assign-operator-notfound-red.png)

**GREEN:** 

![GREEN](skyrescue-tdd/docs/evidence/assign-operator-notfound-green.png)

#### Ciclo TDD - operador con otra misión activa

**RED:** 

![RED](skyrescue-tdd/docs/evidence/assign-active-red.png)

**GREEN:** 

![GREEN](skyrescue-tdd/docs/evidence/assign-active-green.png)

### C. Cierre de misiones (completeMission)

#### Ciclo TDD - completar una misión activa

**RED:** 

![RED](skyrescue-tdd/docs/evidence/complete-active-red.png)

**GREEN:** 

![GREEN](skyrescue-tdd/docs/evidence/complete-active-green.png)

#### Ciclo TDD - completar una misión inexistente

**RED:** 

![RED](skyrescue-tdd/docs/evidence/complete-notfound-red.png)

**GREEN:** 

![GREEN](skyrescue-tdd/docs/evidence/complete-notfound-green.png)

## Cobertura 

![GREEN](skyrescue-tdd/docs/evidence/coverage-final.png)


## Análisis SonarQube

### Analisis inicial SonarQube
**Quality Gate:**  Passed
**Coverage Jacoco:** 85.58% (89/104 líneas)
**Coverage sonar:** 84.3%
**Duplicación:** 0.0%
**Issues:** 0 Security, 2 Reliability, 13 Maintainability

![Dashboard SonarQube](skyrescue-tdd/docs/evidence/sonar-dashboard.png)

