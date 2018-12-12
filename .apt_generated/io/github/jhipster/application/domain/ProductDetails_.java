package io.github.jhipster.application.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductDetails.class)
public abstract class ProductDetails_ {

	public static volatile SingularAttribute<ProductDetails, String> manuName;
	public static volatile SingularAttribute<ProductDetails, Integer> productId;
	public static volatile SingularAttribute<ProductDetails, LocalDate> productManuDate;
	public static volatile SingularAttribute<ProductDetails, Long> id;
	public static volatile SingularAttribute<ProductDetails, Integer> manuId;
	public static volatile SingularAttribute<ProductDetails, String> productName;

}

