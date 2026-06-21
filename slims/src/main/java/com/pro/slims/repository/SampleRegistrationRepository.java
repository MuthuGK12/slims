import org.springframework.data.jpa.repository.Query;

public interface SampleRegistrationRepository extends JpaRepository<SampleRegistration, Long> {

    Optional<SampleRegistration> findBySampleNo(String sampleNo);

    boolean existsBySampleNo(String sampleNo);

    SampleRegistration findTopByOrderBySampleIdDesc();
}