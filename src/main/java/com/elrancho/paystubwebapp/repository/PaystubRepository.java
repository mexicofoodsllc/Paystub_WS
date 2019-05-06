package com.elrancho.paystubwebapp.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.elrancho.paystubwebapp.entity.Paystub;
import com.elrancho.paystubwebapp.entity.PaystubId;

public interface PaystubRepository extends JpaRepository<Paystub,PaystubId>{
	
	List<Paystub> findByEmployeeId(int employeeId);
}
