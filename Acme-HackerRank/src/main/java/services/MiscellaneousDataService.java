
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MiscellaneousDataRepository;
import domain.MiscellaneousData;

@Service
@Transactional
public class MiscellaneousDataService {

	// Managed repository ------------------------------------------------

	@Autowired
	private MiscellaneousDataRepository	miscellaneousDataRepository;


	// Other supporting services -----------------------------------------

	// Constructors ------------------------------------------------------

	public MiscellaneousDataService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	// Other business methods --------------------------------------------

	// Ancillary methods -------------------------------------------------

	protected Collection<MiscellaneousData> copy(final Collection<MiscellaneousData> miscellaneousData) {
		final Set<MiscellaneousData> result = new HashSet<>();

		for (final MiscellaneousData m : miscellaneousData) {
			final MiscellaneousData copy = new MiscellaneousData();

			copy.setAttachments(m.getAttachments());
			copy.setText(m.getText());

			result.add(copy);
		}

		return result;
	}
}
