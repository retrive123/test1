package io.github.jhipster.application.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuthenticKey.class)
public abstract class AuthenticKey_ {

	public static volatile SingularAttribute<AuthenticKey, Boolean> assignmentStatus;
	public static volatile SingularAttribute<AuthenticKey, Boolean> validStatus;
	public static volatile SingularAttribute<AuthenticKey, String> uniqueKey;
	public static volatile SingularAttribute<AuthenticKey, Long> id;
	public static volatile SingularAttribute<AuthenticKey, LocalDate> validationdate;
	public static volatile SingularAttribute<AuthenticKey, ProductDetails> productDetails;

}

